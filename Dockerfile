FROM eclipse-temurin:21-jdk-jammy AS build
COPY . /source
WORKDIR /source
ARG GITHUBPACKAGESUSERNAME
ARG GITHUBPACKAGESPASSWORD
ENV ORG_GRADLE_PROJECT_GitHubPackagesUsername=$GITHUBPACKAGESUSERNAME
ENV ORG_GRADLE_PROJECT_GitHubPackagesPassword=$GITHUBPACKAGESPASSWORD
RUN ./gradlew build

FROM python:3.12.2-slim AS base

ENV JAVA_HOME=/opt/java/openjdk
COPY --from=build $JAVA_HOME $JAVA_HOME
ENV PATH="${JAVA_HOME}/bin:${PATH}"

ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1

ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --shell "/sbin/nologin" \
    --uid "${UID}" \
    appuser

RUN --mount=type=cache,target=/root/.cache/pip \
    --mount=type=bind,source=python/requirements.txt,target=python/requirements.txt \
    python -m pip install -r python/requirements.txt

WORKDIR /home/appuser

COPY --from=build /source/build/libs/*.jar ./myfitnesspal-api-server.jar

COPY python/mfp_bridge.py .
RUN chown appuser mfp_bridge.py

COPY python/cookie_bridge.py .
RUN chown appuser cookie_bridge.py

USER appuser

CMD "java" "-jar" "myfitnesspal-api-server.jar"