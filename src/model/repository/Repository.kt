package com.diver6ty.chargetapbackend.model.repository

import com.diver6ty.chargetapbackend.model.Car
import com.diver6ty.chargetapbackend.model.PowerUnit
import com.diver6ty.chargetapbackend.model.Station
import com.diver6ty.chargetapbackend.model.User

object Repository {
    val mockStations = listOf(
        Station(
            1,
            "Pritax",
            "Aleea Slănic 5, Cluj-Napoca 400000",
            "https://geo2.ggpht.com/cbk?panoid=-Kd2092SedhYWdflPtkSHQ&output=thumbnail&cb_client=search.gws-prod.gps&thumb=2&w=408&h=240&yaw=318.7198&pitch=0&thumbfov=100",
            46.7701333,
            23.6189573,
            3,
            19
        ),
        Station(
            2,
            "MOL Dorobanților",
            "Calea Dorobanților 58-60, Cluj-Napoca 400117",
            "https://lh5.googleusercontent.com/p/AF1QipN6fmsNBdl6x2lkSF6jIkkBefg25amiFDaloxXf=w408-h306-k-no",
            46.7727902,
            23.6061202,
            4,
            512
        ),
        Station(
            3,
            "OMV Mărăști",
            "Piața Mărăști, Cluj-Napoca 400609",
            "https://lh5.googleusercontent.com/p/AF1QipN9gj8inP73vIc-Ee3jPVjKn9aHiyG__i412ZE-=w408-h306-k-no",
            46.777941,
            23.6153349,
            4,
            944
        )
    )

    val mockPowerUnits = listOf(
        PowerUnit(
            1,
            2,
            44,
            1.23,
            2,
            0
        ),
        PowerUnit(
            2,
            2,
            22,
            0.50,
            1,
            0
        ),
        PowerUnit(
            3,
            2,
            50,
            1.99,
            4,
            0
        ),
        PowerUnit(
            4,
            1,
            30,
            1.09,
            2,
            0
        ),
        PowerUnit(
            5,
            1,
            10,
            0.10,
            1,
            0
        ),
        PowerUnit(
            6,
            3,
            50,
            1.79,
            3,
            0
        ),
        PowerUnit(
            7,
            3,
            22,
            0.90,
            2,
            0
        )
    )

    val mockUsers = listOf(
        User(
            1,
            "Vlad Truta",
            "vladtruta@icloud.com",
            "12345678",
            "http://kingdommanmag.com/wp-content/uploads/bfi_thumb/employee-01-nsdul717kdt4yd0uufr3psx3hec9uzmwy7gleu3vv0.jpg"
        ),
        User(
            2,
            "Dan Bagaian",
            "bagaiandan@gmail.com",
            "password",
            "http://swipemarket.com/wp-content/uploads/2014/06/Untitled-6.jpg"
        ),
        User(
            3,
            "George Popescu",
            "georgepopescu@gmail.com",
            "lololol",
            "https://media-exp1.licdn.com/dms/image/C5103AQFDNR3NlfVS0g/profile-displayphoto-shrink_200_200/0?e=1584576000&v=beta&t=cWJBxUBvYuXKYG95atS3F8v-R3EnlSyvgDRsj8NtJ1s"
        )
    )

    val mockCars = listOf(
        Car(
            1,
            2019,
            "Tesla",
        "Model 3",
        "Standard Range (Red)",
            "CJ 12 ABC",
            50,
            350,
            "https://apollo-frankfurt.akamaized.net/v1/files/ggz4qrtneg9t-RO/image;s=644x461",
            2
        ),
        Car(
            2,
            2016,
            "Nissan",
            "Leaf",
            "MY (White)",
            "CJ 34 DEF",
            30,
            172,
            "https://dealerimages.dealereprocess.com/image/upload/819716.jpg",
            1
        ),
        Car(
            3,
            2019,
            "Renault",
            "Zoe",
            "ZE50 (Blue)",
            "CJ 56 GHI",
            52,
            389,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/2017-03-07_Geneva_Motor_Show_1225.JPG/1280px-2017-03-07_Geneva_Motor_Show_1225.JPG",
            1
        ),
        Car(
            4,
            2017,
            "Volkswagen",
            "e-Golf",
            "SEL Premium (White)",
            "CJ 78 JKL",
            36,
            201,
            "https://car-images.bauersecure.com/pagefiles/71992/vw_egolf_2017_04.jpg",
            3
        ),
        Car(
            5,
            2019,
            "Porsche",
            "Taycan",
            "Mission E (Red)",
            "CJ 89 MNO",
            93,
            463,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Porsche_Taycan_IAA_2019_JM_0547.jpg/1280px-Porsche_Taycan_IAA_2019_JM_0547.jpg",
            2
        )
    )
}