import browser_cookie3
import json
import os

COOKIE_DOMAINS = [
    "myfitnesspal.com",
    "www.myfitnesspal.com",
]

cookies_db_path = os.environ.get("MFP_PYTHON_COOKIE_DB_PATH", None)

browser_cookiejar = browser_cookie3.firefox(cookies_db_path)

cookie_list = []
for cookie in browser_cookiejar:
    if cookie.domain in COOKIE_DOMAINS:
        cookie_dict = {
            'version': cookie.version,
            'name': cookie.name,
            'value': cookie.value,
            'port': cookie.port,
            'port_specified': cookie.port_specified,
            'domain': cookie.domain,
            'domain_specified': cookie.domain_specified,
            'domain_initial_dot': cookie.domain_initial_dot,
            'path': cookie.path,
            'path_specified': cookie.path_specified,
            'secure': cookie.secure,
            'expires': cookie.expires,
            'discard': cookie.discard,
            'comment': cookie.comment,
            'comment_url': cookie.comment_url,
            'rest': cookie._rest,
            'rfc2109': cookie.rfc2109
        }
        cookie_list.append(cookie_dict)

print(json.dumps(cookie_list))