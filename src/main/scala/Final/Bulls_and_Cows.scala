package Final

object Bulls_and_Cows extends App {
  //val BullsAndCowsGame = new BullsAndCows

  //BullsAndCowsGame.Play

  val db = new BullsAndCowsDatabase("src/resources/db/bullsandcows.db")
  db.Migrate()
  db.insertPlayer("Marija")
  db.insertPlayer("Nikita")
}
