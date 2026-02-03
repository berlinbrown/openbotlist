# Openbotlist Social (Umbra Social)

Openbotlist (a.k.a. Umbra Social) is an open-source community discussion and URL-posting platform. It is a lightweight, extensible Java web application with JRuby-based view logic and a MySQL-backed data store.

---

## 🔧 Key Features

- Java-based web application (Spring MVC + JSP)
- JRuby integration for view logic
- Hibernate for persistence
- Deployable as a WAR to Apache Tomcat
- Flexible SQL-based schema (see `db/` scripts)

---

## ✅ Requirements

- Java 21 (project is configured for Java 21)
- Apache Maven 3.x
- MySQL for local development
- Apache Tomcat (or any servlet container that supports WAR deployment)
- JRuby (optional, for running JRuby specs)

---

## Quick Start

1. Build the project

```bash
cd botlist
mvn clean package
```

2. Deploy the produced WAR

```bash
cp target/umbrasocial.war $TOMCAT_HOME/webapps/
# then start Tomcat
```

3. Initialize the database

- Run SQL scripts in `botlist/db/` (e.g., `create_database.sql`, `create_tables.sql`) and any patches in `db/patch/` as needed.
- Copy `src/main/webapp/example_botlist_config.properties` to `botlist_config.properties` and add your DB credentials.

---

## Running Tests

- Unit and integration tests: `mvn test`
- Note: In some local setups, `mvn test` may fail to compile if required repository credentials or internal artifact mirrors are missing. CI is configured to run a full validation in an environment with those dependencies.

---

## Development Notes

- The project contains a mix of legacy Ant scripts and modern Maven flows; prefer Maven where possible (`mvn test`, `mvn package`).
- JRuby specs and view logic live under `src/main/webapp/jsps/` and `src/main/webapp/jsps/*.rb`.
- If you change database schema, update `db/` SQL and add a patch under `db/patch/`.

---

## Formatting & Style

- A repository branch `style/reformat-java-jsp` contains a comprehensive reformat of Java sources and JSP whitespace normalization (Eclipse formatter was used successfully).
- To apply the repository's formatter locally:

```bash
cd botlist
mvn net.revelc.code.formatter:formatter-maven-plugin:format
```

---

## Contributing

- Open issues and PRs against `main` (or the branch you forked).
- Run `mvn test` and ensure formatting is applied before submitting a PR.

---

## Documentation & Support

- See `botlist/docs/` for developer notes and quickstart guides (notably `QuickStart.txt`).
- License: see `LICENSE.md`.

---

> Tip: If your local build fails due to missing dependencies or repository access, try running the build in CI or ask a maintainer for repository/mirror credentials.

---

*Last updated: Feb 2, 2026*
