import myfitnesspal as mfp
import browser_cookie3
from datetime import datetime
import sys
import json
import os

args = sys.argv

cmd = args[1].lower()

cookies_db_path = os.environ['MFP_PYTHON_COOKIE_DB_PATH']

client = mfp.Client(browser_cookie3.firefox(cookies_db_path))

if cmd == "day_meals":
    date = datetime.strptime(args[2], "%Y-%m-%d")
    day = client.get_date(date)

    meals = []

    for meal in day.meals:
        raw_entries = meal.get_as_list()
        entries = []
        for entry in raw_entries:
            description = entry["name"]

            if meal.name:
                parts = description.split(", ")
                description = parts[0]
                if len(parts) > 1:
                    amount_unit = parts[1]
                    parts = amount_unit.split(" ")
                    amount = float(parts[0])
                    unit = " ".join(parts[1:])
                else:
                    try:
                        amount = float(parts[0])
                    except ValueError:
                        amount = 0.0
                    unit = ""
                    
                parts = description.split(" - ")
                if len(parts) > 1:
                    name = parts[1]
                    brand = parts[0]
                else:
                    name = parts[0]
                    brand = ""

            nutrition = entry["nutrition_information"]
            entries.append(
                {
                    "name": name,
                    "brand": brand,
                    "amount": amount,
                    "unit": unit,
                    "nutrition_information": nutrition,
                }
            )

        meals.append({"name": meal.name, "entries": entries})

    print(json.dumps(meals))

elif cmd == "metadata":
    metadata = client._get_user_metadata()
    print(json.dumps(metadata))
