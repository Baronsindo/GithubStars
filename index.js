const sqlite3 = require('sqlite3').verbose();
const express = require('express')
var path = require('path');


//smiya dyal DataBase
var file = "MDB.db"

const app = express()
const port = process.env.PORT || 3000 


/************************************************************************************
* 
*                                         Routing
* 
* ***********************************************************************************/

// Routing ROUTE l3adi
app.get('/', (req, res) =>  res.sendFile(path.join(__dirname + '/page.html')))

// Singer 
app.get('/singers', (req, res) => {
    try{
    tab = []
    if (req.query.id && req.query.col) {
        // One Singer
        getSinger("select "+req.query.col+" from Singers where id =" + req.query.id, res)
    } else if(req.query.col) {
        // all singers
        getSinger("select "+req.query.col+" from Singers", res)
    }
    else {
        // all singers
        getSinger("select * from Singers", res)
    }
    }
    catch(){
        res.send("Err Boy")
    }
})

// Singer 
app.get('/songs', (req, res) => {
    tab = []
    if (req.query.id_singer && req.query.col) {
        // One Singer
        getSinger("select "+req.query.col+" from Songs where singer_id =" + req.query.id, res)
    } else if(req.query.col) {
        // all singers from a singer 
        getSinger("select "+req.query.col+" from Songs", res)
    }
    else {
        // all songs
        getSinger("select * from Songs", res) 
    }
})


app.listen(port, () => console.log(`Example app listening on port ${port}!`))



// Kan3tiha Request o Query Kat9adliya query 3la Chkel json o katredha liya 3la chkel response
function getSinger(MyQuery, MyRes) {
    console.log(MyQuery)
    var db = new sqlite3.Database(file);
    var tibo = []
    db.all(MyQuery, function (err, rows) {
        rows.forEach(function (row) {
            //console.log(row);
            tibo.push(row)
        })
        MyRes.send(tibo)
    });
    db.close();
}
