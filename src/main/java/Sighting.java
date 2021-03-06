import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import java.text.DateFormat;

public class Sighting implements MyInterface{
  private int animal_id;
  private Timestamp timeofsighting;
  private String location;
  private String ranger_name;
  private int id;
  private boolean endangered;

  public Sighting(int animal_id, String location, String ranger_name, boolean endangered) {
    this.animal_id = animal_id;
    this.location = location;
    this.ranger_name = ranger_name;
    this.id = id;
    this.endangered = endangered;
  }

  public int getId() {
    return id;
  }

  public int getAnimalId() {
    return animal_id;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return ranger_name;
  }

  public Timestamp getTimeOfSighting(){
    return timeofsighting;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getAnimalId() == (newSighting.getAnimalId()) && this.getLocation().equals(newSighting.getLocation()) && this.getRangerName().equals(newSighting.getRangerName());
    }
  }
  // For MyInterface
  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location, ranger_name, timeofsighting, endangered) VALUES (:animal_id, :location, :ranger_name, now(), :endangered);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("animal_id", this.animal_id)
        .addParameter("location", this.location)
        .addParameter("ranger_name", this.ranger_name)
        .addParameter("endangered",  this.endangered)
        .throwOnMappingFailure(false)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Sighting> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .executeAndFetch(Sighting.class);
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id=:id;";
      Sighting sighting = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Sighting.class);
      return sighting;
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }
  // For MyInterface
  @Override
  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM sightings WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }
}
