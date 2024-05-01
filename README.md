# MyFoodPal Server

This is a MyFitnessPal Server which provides a REST API to access MyFitnessPal food data.

This project makes use of [this Java myfitnesspal API](https://github.com/marcosav/myfitnesspal-api) I made
and optionally [python-myfitnesspal](https://github.com/coddingtonbear/python-myfitnesspal).

## Docker

Docker image available at [Docker Hub](https://hub.docker.com/repository/docker/marcosav/myfitnesspal-api-server/general).

## Configuration

### Environment variables

| Property                        | Description                       | Info/Default value                                                                  |
|---------------------------------|-----------------------------------|-------------------------------------------------------------------------------------|
| `MFP_IMPLEMENTATION`            | `library` or `python`             | *`library`*                                                                         |
| `MFP_PYTHON`                    | Python path                       | *`python3`*                                                                         |
| `MFP_PYTHON_PATH`               | MFP bridge python scripts path    | *If using `python` implementation, defaults to `/home/appuser/mfp_bridge.py`*       |
| `MFP_PYTHON_COOKIE_LOADER_PATH` | Cookie bridge python scripts path | *If using `library` implementation, , defaults to `/home/appuser/cookie_bridge.py`* |
| `MFP_PYTHON_COOKIE_DB_PATH`     | Cookies Sqlite DB path            | `cookies.sqlite`                                                                    |