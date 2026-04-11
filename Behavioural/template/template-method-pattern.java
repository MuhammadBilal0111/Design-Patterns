abstract class BaseGameLoader {
  public void load() {
    byte[] data = loadLocalData();
    createObjects(data);           // Make sure this is called
    downloadAdditionalFiles();
    initializeProfiles();
    cleanTempFiles();
  }
  protected abstract byte[] loadLocalData();
  protected abstract void createObjects(byte[] data);
  protected abstract void downloadAdditionalFiles();
  protected abstract void initializeProfiles();
  protected void cleanTempFiles() {
    System.out.println("Cleaning temp files...");
  }
}
class WorldOfWarcraftLoader extends BaseGameLoader {
  @Override
  protected byte[] loadLocalData() {
    System.out.println("Loading local data for World of Warcraft...");
      return new byte[0]; // Simulated data
  }
  @Override
  protected void createObjects(byte[] data) {
    System.out.println("Creating game objects for World of Warcraft...");
  }
  @Override
  protected void downloadAdditionalFiles() {
    System.out.println("Downloading additional files for World of Warcraft...");
  }
  @Override
  protected void initializeProfiles() {
    System.out.println("Initializing profiles for World of Warcraft...");
  }
}
class DiabloLoader extends BaseGameLoader {
  @Override
  protected byte[] loadLocalData() {
    System.out.println("Loading local data for Diablo...");
      return new byte[0]; // Simulated data
  }
  @Override
  protected void createObjects(byte[] data) {
    System.out.println("Creating game objects for Diablo...");
  }
  @Override
  protected void downloadAdditionalFiles() {
    System.out.println("Downloading additional files for Diablo...");
  }
  @Override
  protected void initializeProfiles() {
    System.out.println("Initializing profiles for Diablo...");
  }
}

public class Main {
  public static void main(String[] args) {
    BaseGameLoader wowLoader = new WorldOfWarcraftLoader();
    wowLoader.load();

    System.out.println();

    BaseGameLoader diabloLoader = new DiabloLoader();
    diabloLoader.load();
  }
}