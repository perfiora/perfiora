## Hi there 👋

<!--
**perfiora/perfiora** is a ✨ _special_ ✨ repository because its `README.md` (this file) appears on your GitHub profile.

Here are some ideas to get you started:

- 🔭 I’m currently working on ...
- 🌱 I’m currently learning ...
- 👯 I’m looking to collaborate on ...
- 🤔 I’m looking for help with ...
- 💬 Ask me about ...
- 📫 How to reach me: ...
- 😄 Pronouns: ...
- ⚡ Fun fact: ...
-->

## Usage

Perfiora is a CLI tool for database introspection and analysis. Connect to your database using a JDBC connection string and retrieve information about your database server.

### Get Database Information

Use the `info` command to retrieve comprehensive information about both the DBMS (Database Management System) and the connected database:

**MySQL Example:**
```shell
perfiora info jdbc:mysql://user:password@localhost:3306/mydatabase
```

**PostgreSQL Example:**
```shell
perfiora info jdbc:postgresql://user:password@localhost:5432/mydatabase
```

**Connection String Format:**
The connection string follows the JDBC URL format:
```
jdbc:<protocol>://[user[:password]@]host[:port]/database
```

- **Protocol**: `mysql` or `postgresql`
- **User/Password**: Optional credentials (if omitted, defaults may be used)
- **Host**: Database server hostname or IP address
- **Port**: Optional port number (defaults to 3306 for MySQL, 5432 for PostgreSQL)
- **Database**: Database name

**Example Output:**
```
2025-12-03 11:36:42 [INFO] io.perfiora.App - Perfiora
2025-12-03 11:36:42 [INFO] io.perfiora.App - Command: info
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - === DBMS Information ===
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - Product Name: MySQL
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - Product Version: 8.4.7
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - Driver Name: MySQL Connector/J
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - Driver Version: 9.5.0
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - === Database Information ===
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - Database: mydatabase
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - Schema: mydatabase
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - User: user@localhost
2025-12-03 11:36:42 [INFO] io.perfiora.command.InfoCommand - URL: jdbc:mysql://localhost:3306/mydatabase
```

The `info` command provides:
- **DBMS Information**: Product name, version, JDBC driver details
- **Database Information**: Database name, schema, connected user, connection URL