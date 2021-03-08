const express = require('express');
const app     = express();
const cors    = require("cors");

app.use(express.json());

app.listen(85, () => {
    console.log('Serveur Express à l écoute sur le port 85');
});

let corsOption = {
    origin: ["http://localhost"]
}

app.use(cors(corsOption));

const MongoClient = require("mongodb").MongoClient;
const url         = 'mongodb://localhost:27017';
const dbName      = 'GestiBank';
let db;

MongoClient.connect(url, function(err, client){
    console.log(("Connexion réussi avec Mongo"));
    db = client.db(dbName);
});

// app.get("/login/:mail", async (req, res) => {
//     const query = {
//         login: req.body.login,
//         password: req.body.password
//     }
//     await db.collection("user").findOne(query, (err, result) => {
//         if(result != null) {
//             const objToSend = {
//                 login: result.login,
//                 password: result.password,
//                 activated: result.activated
//             }
//             res.status(200).json(objToSend);
//         } else {
//             res.status(400);
//         }
//     });
// });

// app.get("/user", (req, res) => {
//     db.collection("user").find({"role" : "CLIENT"}).toArray(function (err, docs){
//         if(err){
//             console.log(err);
//             throw err;
//         }
//         res.status(200).json(docs);
//     })
// })
app.post("/login/:mail/:password", async (req, res) => {
    const mail = req.params.mail;
    const pass = req.params.password
    try {
        const user = await db.collection("user").findOne({"mail":mail, "password": pass});
        res.status(200).json(user);
    } catch (error) {
        console.log(error);
        throw error;
    }
});


app.get("/user/:role", (req, res) => {
    const role = req.params.role
    db.collection("user").find({"role": role}).toArray(function (err, docs) {
        if(err){
            console.log(err);
            throw err
        }
        res.status(200).json(docs);
    });
});

app.get("/user", (req, res) => {
    db.collection("user").find().toArray(function(err, docs){
        if(err){
            console.log(err);
            throw err
        }
        res.status(200).json(docs);
    });
});

app.post("/user", async (req, res) => {
    try{
        const userData = req.body;
        const user = await db.collection("user").insertOne(userData);
        console.log("test");
        res.status(200).json(user);
    }catch(error){
        console.log(error);
        throw error;
    }
});

app.delete("/user/agent/:mail/:matricule", async(req, res) => {
    try {
        const mail = req.params.mail;
        const matr = req.params.matricule;
        const user = await db.collection("user").deleteOne({"mail" : mail, "matricule" : matr});
        res.status(200).json(user);
    } catch (error) {
        console.log(error);
        throw error;
    }
});

app.put("/user/agent/:mail", async(req, res) => {
    try {
        const mail = req.params.mail;

        const editU = req.body;
        // const user = await db.collection("user").replaceOne({"mail":mail, "matricule": matr}, editU);
        const user = await db.collection("user").updateOne({"mail":mail}, 
        {$set: 
            {
            "name": editU.name,
            "firstname": editU.firstname, 
            "mail": editU.mail,
            "activated": editU.activated,
            "role": editU.role,
            "password": editU.password,
            "matricule": editU.matricule,
            }});
        res.status(200).json(user);
    } catch (error) {
        console.log(error);
        throw error;
    }
});

app.get("/user/client/:activated", async(req, res) => {
    const activated = req.params.activated
    db.collection("user").find({"activated": activated}).toArray(function (err, docs) {
        if(err){
            console.log(err);
            throw err
        }
        res.status(200).json(docs);
    });
});

app.put("/user/client/:mail", async(req, res) => {
    try {
        const mail = req.params.mail;
        const agent = req.body.agent;
        const user = await db.collection("user").updateOne({"mail":mail}, 
        {$set: {
            "agent": agent,
    }});

        res.status(200).json(user);
    } catch (error) {
        console.log(error);
        throw error;
    }
});

app.put("/user/client/activate/:mail", async(req, res) => {
    try {
        const mail = req.params.mail;
        const status = req.body.activated;
        const pass = req.body.password;
        const user = await db.collection("user").updateOne({"mail": mail},
        {
            $set:{
                "password": pass,
                "activated": status
            }
        });
        console.log(req.body);
        res.status(200).json(user);
    } catch (error) {
        console.log(error);
        throw error;
    }
})
