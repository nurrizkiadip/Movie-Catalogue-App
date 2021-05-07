package com.nurrizkiadip_a1201541.moviecatalogue.utils

import com.nurrizkiadip_a1201541.moviecatalogue.BuildConfig.BASE_URL_MOVIEDB_IMAGE
import com.nurrizkiadip_a1201541.moviecatalogue.data.MovieEntity
import com.nurrizkiadip_a1201541.moviecatalogue.data.TvShowEntity

object MoviesData {
    fun generateMovieData(): ArrayList<MovieEntity> {
        return ArrayList<MovieEntity>().apply{
            /*1*/
            add(MovieEntity(
                "460465",
                "${BASE_URL_MOVIEDB_IMAGE}/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "9008.356",
                "2021-04-07",
                "en"
            ))
            /*2*/
            add(MovieEntity(
                "404368",
                "${BASE_URL_MOVIEDB_IMAGE}qEnH5meR381iMpmCumAIMswcQw2.jpg",
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "83.386",
                "2018-11-20",
                "en"
            ))
            /*3*/
            add(MovieEntity(
                "324857",
                "${BASE_URL_MOVIEDB_IMAGE}iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "103.755",
                "2018-12-06",
                "en"
            ))
            /*4*/
            add(MovieEntity(
                "299536",
                "${BASE_URL_MOVIEDB_IMAGE}7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "360.181",
                "2018-04-25",
                "en"
            ))
            /*5*/
            add(MovieEntity(
                "297802",
                "${BASE_URL_MOVIEDB_IMAGE}5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "135.199",
                "2018-12-07",
                "en"
            ))
            /*6*/
            add(MovieEntity(
                "166428",
                "${BASE_URL_MOVIEDB_IMAGE}xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "90.319",
                "2019-01-03",
                "en"
            ))
            /*7*/
            add(MovieEntity(
                "505954",
                "${BASE_URL_MOVIEDB_IMAGE}jqBIHiSglRfNxjh1zodHLa9E7zW.jpg",
                "T-34",
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "33.532",
                "2018-12-27",
                "ru"
            ))
            /*8*/
            add(MovieEntity(
                "480530",
                "${BASE_URL_MOVIEDB_IMAGE}v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "109.815",
                "2018-11-21",
                "en"
            ))
            /*9*/
            add(MovieEntity(
                "424694",
                "${BASE_URL_MOVIEDB_IMAGE}lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "75.591",
                "2018-10-24",
                "en"
            ))
            /*10*/
            add(MovieEntity(
                "438650",
                "${BASE_URL_MOVIEDB_IMAGE}hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "112.944",
                "2019-02-07",
                "en"
            ))
        }
    }

    fun generateTvShowsData(): ArrayList<TvShowEntity>{
        return ArrayList<TvShowEntity>().apply{
            /*1*/
            add(TvShowEntity(
                "88396",
                "${BASE_URL_MOVIEDB_IMAGE}/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "2640.049",
                "2021-03-19",
                6,
                1,
                "en",
            ))
            /*2*/
            add(TvShowEntity(
                "456",
                "${BASE_URL_MOVIEDB_IMAGE}2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "363.779",
                "1989-12-17",
                706,
                34,
                "en",
            ))
            /*3*/
            add(TvShowEntity(
                "12609",
                "${BASE_URL_MOVIEDB_IMAGE}tZ0jXOeYBWPZ0OWzUhTlYvMF7YR.jpg",
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "13.688",
                "1986-02-26",
                153,
                1,
                "ja",
            ))
            /*4*/
            add(TvShowEntity(
                "60735",
                "${BASE_URL_MOVIEDB_IMAGE}lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "1274.326",
                "2014-10-07",
                142,
                7,
                "en",
            ))
            /*5*/
            add(TvShowEntity(
                "46261",
                "${BASE_URL_MOVIEDB_IMAGE}jsYTctFnK8ewomnUgcwhmsTkOum.jpg",
                "Fairy Tail",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                "35.601",
                "2009-10-12",
                328,
                8,
                "ja",
            ))
            /*6*/
            add(TvShowEntity(
                "1434",
                "${BASE_URL_MOVIEDB_IMAGE}eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "271.109",
                "1999-01-31",
                368,
                21,
                "en",
            ))
            /*7*/
            add(TvShowEntity(
                "1412",
                "${BASE_URL_MOVIEDB_IMAGE}gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "205.545",
                "2012-10-10",
                170,
                8,
                "en",
            ))
            /*8*/
            add(TvShowEntity(
                "1906",
                "${BASE_URL_MOVIEDB_IMAGE}kE30UWJvJZ03KIIJMmL6m6YoG0l.jpg",
                "Shameless",
                "The story of a young group of siblings pretty much abandoned by their parents, surviving by their wits - and humor - on a rough Manchester council estate. Whilst they won't admit it, they need help and find it in Steve, a young middle class lad who falls for Fiona, the oldest sibling, and increasingly finds himself drawn to this unconventional and unique family. Anarchic family life seen through the eyes of an exceptionally bright fifteen year old, who struggles to come of age in the context of his belligerent father, closeted brother, psychotic sister and internet porn star neighbors.",
                "30.085",
                "2004-01-13",
                138,
                11,
                "en",
            ))
            /*9*/
            add(TvShowEntity(
                "75006",
                "${BASE_URL_MOVIEDB_IMAGE}scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "260.806",
                "2019-02-15",
                20,
                2,
                "en",
            ))
            /*10*/
            add(TvShowEntity(
                "1622",
                "${BASE_URL_MOVIEDB_IMAGE}KoYWXbnYuS3b0GyQPkbuexlVK9.jpg",
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "355.587",
                "2005-09-13",
                327,
                15,
                "en",
            ))
        }
    }


}