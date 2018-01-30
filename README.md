# Sqlite-sjs Client for Node.js App
[![Build Status](https://travis-ci.org/PepRoll/sqlite-sjs.svg?branch=master)](https://travis-ci.org/PepRoll/sqlite-sjs)
> A wrapper library that adds SQL-based migrations and some additional methods to
> [better-sqlite3](https://github.com/JoshuaWise/better-sqlite3)
> api documentations [docs](https://github.com/JoshuaWise/better-sqlite3/wiki/API)


## Install

NPM project:

`npm install sqlite-sjs`

SBT project:

`libraryDependencies += "me.peproll" %%% "sqlite-sjs" % "0.0.3"`


## Usage


From js code:
```js
var Database = require('sqlite-sjs');
var db = new Database('foobar.db', options);

var row = db.prepare('SELECT * FROM users WHERE id=?').get(userId);
console.log(row.firstName, row.lastName, row.email);
```

From scala.js code:
```scala
@ScalaJSDefined
trait User extends js.Object {
  def name: String = js.native
}

val db = Sqlite("foobar.db", new ConnectionOptions())
var row = db.prepare("SELECT * FROM users WHERE id=?").get[User](userId)
println(s"Username: ${row.name}")
```

## Migrations


## Some stuff
