# API collegues-api

## Authentification

Authentification avec le rôle "ADMIN" nécessaire pour les requêtes POST et PATCH (sauf pour la requête POST
d'authentification).

Le rôle "USER" ou "ADMIN" est nécessaire pour l'ensemble des autres requêtes, sauf pour l'accès à la base de données h2
et à la page d'authentification.


Requêtes d'authentification :

--> Pour se connecter avec le rôle "ADMIN"
[POST] https://jbmerand-collegues-api.herokuapp.com/auth
```JSON
{
    "identifiant" : "u1",
    "motDePasse" : "pass1"
}
```

--> Pour se connecter avec le rôle "USER"
[POST] https://jbmerand-collegues-api.herokuapp.com/auth
```JSON
{
    "identifiant" : "u2",
    "motDePasse" : "pass2"
}
```

Réponse en cas de réussite :

Code `200`

```
```

## Ajouter un collègue

Requête :

[POST] https://jbmerand-collegues-api.herokuapp.com/collegues

```JSON
{
	"nom" : "Lombard",
	"prenoms" : "Kévin, Lucien",
	"email" : "kevin.lombard@mail.com",
	"dateDeNaissance" : "1989-11-11",
	"photoUrl" : "https://randomuser.me/api/portraits/men/76.jpg",
	"identifiant" : "kevin411",
	"motDePasse" : "kevinMDP",
	"role" : "USER"
}
```

Réponse en cas de réussite :

code `201`

```JSON
{
    "matricule": "f5cde1ec-290a-4e7b-932d-688098a223cd",
    "nom": "Lombard",
    "prenoms": "Kévin, Lucien",
    "roles": [
        "USER"
    ]
}
```

Réponse en cas d'échec :

code `404`

```
ERREUR : au moins un des champs est mal renseigné. 
Field error in object 'collegueDtoPost' on field 'motDePasse': rejected value [gb]; codes [Size.collegueDtoPost.motDePasse,Size.motDePasse,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [collegueDtoPost.motDePasse,motDePasse]; arguments []; default message [motDePasse],32,6]; default message [le mot de passe doit comporter de 6 à 32 caractères]
```
```
ERREUR : le rôle doit être ADMIN ou USER
```

## Consulter les informations du profil actuellement connecté

Requête :

[GET] https://jbmerand-collegues-api.herokuapp.com/auth/user

Réponse en cas de réussite :

Code `200`

```JSON
{
    "matricule": "d747a0b8-a68e-49be-8b8d-572bf3a36ca0",
    "nom": "Dupuis",
    "prenoms": "James, Arnold",
    "roles": [
        "ROLE_ADMIN",
        "ROLE_USER"
    ]
}
```

## Consulter le matricule des collègues d'un nom précis

Requête :

[GET] https://jbmerand-collegues-api.herokuapp.com/collegues?nom={nomDuCollegueIci}

Réponse en cas de réussite :

Code `200`

```JSON
[
    "f5cde1ec-290a-4e7b-932d-688098a223cd"
]
```

## Consulter les informations d'un collègue

Requête :

[GET] https://jbmerand-collegues-api.herokuapp.com/collegues/{matriculeDuCollegueIci}

Réponse en cas de réussite :

Code `200`

```JSON
{
    "matricule": "e72f0cbe-dcb9-41b4-ae11-29db3ea4a705",
    "nom": "Durand",
    "prenoms": "Oliver, Pierre",
    "roles": [
        "ROLE_USER"
    ]
}
```

Réponse en cas d'échec :

Code `404`

```
ERREUR : Ce matricule correspond à aucun collègue.
```

## Modifier l'email et/ou l'url de la photo d'un collègue

Requête :

[PATCH] https://jbmerand-collegues-api.herokuapp.com/collegues/{matricule}

```JSON
{
	"email" : "dupuis.james@globalmail.com",
	"photoUrl" : "https://randomuser.me/dupuis.jpg"
}
```

Réponse en cas de réussite :

Code `200`

```JSON
{
    "matricule": "be27ddbc-798c-4f80-bfa5-9acfe233c76d",
    "nom": "Dupuis",
    "prenoms": "James, Arnold",
    "photoUrl": "https://randomuser.me/dupuis.jpg",
    "email": "dupuis.james@globalmail.com"
}
```

Réponse en cas d'échec :

Code `404`

```
ERREUR : Url de la photo incorrecte (elle ne commence pas par http).
```
```
ERREUR : Email incorrect (il ne respecte pas le format).
``