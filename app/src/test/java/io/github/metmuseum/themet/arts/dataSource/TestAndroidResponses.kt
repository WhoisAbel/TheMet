package io.github.metmuseum.themet.arts.dataSource

val successfulArtIdListResponse = """
{
    "total": 7,
    "objectIDs": [
        436524,
        484935,
        437112,
        210191,
        431264,
        397949,
        656530
        ]
}
""".trimIndent()

val successfulArtDetailsListResponse = """
{
    "objectID": 459027,
    "objectName": "Painting",
    "title": "Portrait of a Woman, Possibly a Nun of San Secondo; (verso) Scene in Grisaille",
    "repository": "Metropolitan Museum of Art, New York, NY",
    "accessionNumber": "1975.1.85",
    "objectDate": "ca. 1485–95",
    "primaryImageSmall": "https://images.metmuseum.org/CRDImages/rl/original/DP221483.jpg",
    "department": "Robert Lehman Collection",
    "dimensions": "Overall 4 x 2 7/8 in. (10.2 x 7.3 cm); recto and verso, painted surface 3 3/4 x 2 1/2 in. (9.5 x 6.4 cm)",
    "additionalImages": [
        "https://images.metmuseum.org/CRDImages/rl/original/0347.jpg",
        "https://images.metmuseum.org/CRDImages/rl/original/DT221859.jpg",
        "https://images.metmuseum.org/CRDImages/rl/original/DT221864.jpg",
        "https://images.metmuseum.org/CRDImages/rl/original/174b verso DP221484.jpg"
    ]
}
""".trimIndent()

val errorResponse = "I am not a json :o"