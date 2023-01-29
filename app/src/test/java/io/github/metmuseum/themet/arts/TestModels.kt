package io.github.metmuseum.themet.arts

import io.github.metmuseum.themet.arts.model.ArtDetails
import io.github.metmuseum.themet.arts.model.ArtIdList

val artIdList1 = ArtIdList(
    objectIDs = listOf(
        436524,
        484935,
        437112,
        210191,
        431264,
        397949,
        656530
    ),
    total = 7
)

val artIdList2 = ArtIdList(
    objectIDs = listOf(
        480725,
        486590,
        485308,
        375281,
        705155,
        11922,
        2032,
        343052,
        347980
    ),
    total = 9
)

val artDetails1 = ArtDetails(
    objectID = 459027,
    objectName = "Painting",
    title = "Portrait of a Woman, Possibly a Nun of San Secondo; (verso) Scene in Grisaille",
    repository = "Metropolitan Museum of Art, New York, NY",
    accessionNumber = "1975.1.85",
    objectDate = "ca. 1485–95",
    primaryImageSmall = "https://images.metmuseum.org/CRDImages/rl/original/DP221483.jpg",
    department = "Robert Lehman Collection",
    dimensions = "Overall 4 x 2 7/8 in. (10.2 x 7.3 cm); recto and verso, painted surface 3 3/4 x 2 1/2 in. (9.5 x 6.4 cm)",
    additionalImages = listOf(
        "https://images.metmuseum.org/CRDImages/rl/original/0347.jpg",
        "https://images.metmuseum.org/CRDImages/rl/original/DT221859.jpg",
        "https://images.metmuseum.org/CRDImages/rl/original/DT221864.jpg",
        "https://images.metmuseum.org/CRDImages/rl/original/174b verso DP221484.jpg"
    )
)

val artDetails2 = ArtDetails(
    objectID = 210191,
    objectName = "Vase",
    title = "Vase with sunflowers",
    repository = "Metropolitan Museum of Art, New York, NY",
    accessionNumber = "2000.126",
    objectDate = "1896",
    primaryImageSmall = "https://images.metmuseum.org/CRDImages/eg/original/2-35.9.20a–w_EGDP014589-4594.jpg",
    department = "European Sculpture and Decorative Arts",
    dimensions = "Approximate measurements (framed): L. 21.9 m (71 ft. 10 3/16 in); H. 35 cm (1 ft. 1 3/4 in.). Length previously estimated at 63 ft. (19.2 m).",
    additionalImages = listOf(
        "https://images.metmuseum.org/CRDImages/eg/original/35.9.20a_w_Section1.jpg",
        "https://images.metmuseum.org/CRDImages/eg/original/35.9.20a_w_Section2.jpg",
        "https://images.metmuseum.org/CRDImages/eg/original/35.9.20a_w_Section3.jpg",
        "https://images.metmuseum.org/CRDImages/eg/original/35.9.20a_w_Section4.jpg"
    )
)