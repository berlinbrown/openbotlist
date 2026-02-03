# GitHub Copilot / AI Agent Instructions for OpenBotList (Umbra Social)

## Purpose ✅
Short, focused reference so an AI agent can be productive immediately: build, run, test, and locate domain logic and integration points.

## Quick start — Build & Run 🔧
- Build with Maven (project root = `botlist/`):
  - `cd botlist && mvn clean package`
  - Result: `target/umbrasocial.war` (see `<finalName>` in `pom.xml`).
- Deploy: copy `target/umbrasocial.war` to a Tomcat `webapps` directory (project uses WAR packaging; Spring Boot repackaging is disabled in the pom).
- Legacy alternative: many older scripts use Ant; see `tests/build.xml` and `docs/QuickStart.txt` for historical flows.

## Run & test notes 🧪
- Unit / integration: there are both Maven-driven and legacy Ant/Scala test suites.
  - Prefer `mvn test` for modern Java tests.
  - Older unit/integration suites live under `botlist/tests/` and can be run with Ant if needed.
- JRuby specs: found under `src/main/webapp/lib/ruby/rspec/spec` and typically run via `jruby -S rake` (ensure JRuby installed or use the packaged `jruby-complete` dependency).

## Architecture & core components 🏗️
- Stack: **Java 21**, **Spring (XML-based controllers + servlet)**, **Hibernate**, **JRuby**, **MySQL**, **Tomcat**.
- Important boundaries:
  - Web front-end: `src/main/webapp/` (JSPs, Ruby scripts in `jsps/`, static assets)
  - Spring wiring & controllers: see `web.xml` (context config location) and controller classes under `src/main/java/org/spirit/spring/` (e.g., `BotListIndexController`, `BotListRubyController`).
  - JRuby integration: `BotListRubyController`, `GenericJRubyLoader` use `JRubyEngine` to run `.rb` JSPS under `src/main/webapp/jsps/`.
  - Persistence: Hibernate configs in `hibernate.cfg.xml` and `hibernate.properties`; DB SQL scripts in `db/`.

## Key files to inspect (first triage) 🔎
- `botlist/pom.xml` — main build & dependency declarations (Java version, JRuby, Hibernate)
- `src/main/webapp/web.xml` — context config locations and servlet mappings
- `src/main/webapp/*.xml` — Spring XMLs: `spring-botlist-util.xml`, `botlistings-servlet.xml`, `applicationContext-*.xml`
- `src/main/java/org/spirit/spring/*` — controllers, validators, JRuby bridges
- `src/main/webapp/jsps/*.rb` — JRuby view logic
- `db/*.sql` — schema & seed data (run before first start)
- `logs` configuration: `src/main/webapp/log4j.properties` (jruby logs to `${catalina.base}/logs/openbotlist_ruby.log`)

## Conventions & patterns 📐
- Many controllers extend Spring MVC *form controllers* (SimpleFormController-like patterns) and use XML bean configuration.
- Validation logic uses `org.spirit.spring.validate` package validators; look for `*Validator.java` when adding/changing form behavior.
- JRuby scripts often manipulate request/response data and are invoked from Java controllers — changing Ruby scripts typically requires understanding the Java-to-Ruby contract (`BotListRubyController` and `GenericJRubyLoader`).
- There is a mixture of legacy (Ant, older Tomcat, Lift/Scala mentions) and modern (Maven, Java 21). Prefer changes that are safe for both contexts unless you plan a migration.

## Integration points & external dependencies ⚠️
- MySQL required; use `src/main/webapp/example_botlist_config.properties` as the template (copy to `botlist_config.properties` and set credentials).
- Tomcat is the standard runtime (WAR deploy); do not rely on Spring Boot embedded server because repackaging is disabled.
- Lucene (search) and Acegi/Spring Security are present — be cautious when touching authentication/search code.

## Gotchas & troubleshooting tips 💡
- The codebase contains security warnings in `botlist/docs/QuickStart.txt` — avoid copying the project directly into a production `webapps` directory without proper packaging and configuration.
- If Ruby behavior looks broken, check `log4j` and the `openbotlist_ruby.log` file for JRuby errors.
- When changing DB schema, update `db/` SQL and search for patches under `db/patch/`.

## How to propose changes (for AI agents) ✍️
- For Java controller changes: update `src/main/java/org/spirit/spring/*`, then add/adjust tests under `botlist/tests/unit` and run `mvn test`.
- For Ruby view logic: edit `src/main/webapp/jsps/*.rb`; run JRuby specs if present and verify via integration tests.
- For deployment-related changes: adjust `pom.xml` (packaging and plugin sections) and document commands in `docs/`.

---
> If anything above is unclear or you want more detail in a specific area (JRuby contract, test harness, or packaging), tell me which part to expand and I'll iterate. ✨
