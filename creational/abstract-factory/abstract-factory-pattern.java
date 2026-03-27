// let you produce family of related objects without specifying the concrete classes
interface Gpu{
  void assemble();
}
class MsiGpu implements Gpu{
  @Override
  public void assemble(){
    System.out.println("Assembling MSI GPU");
  }
}
class AsusGpu implements Gpu{
  @Override
  public void assemble(){
    System.out.println("Assembling ASUS GPU");
  }
}
interface Monitor{
  void assemble();
}
class MsiMonitor implements Monitor{
  @Override
  public void assemble(){
    System.out.println("Assembling MSI Monitor");
  }
}
class AsusMonitor implements Monitor{
  @Override
  public void assemble(){
    System.out.println("Assembling ASUS Monitor");
  }
}
abstract class Company {
  public abstract Gpu createGpu(String type);
  public abstract Monitor createMonitor(String type);
}
class MsiCompany extends Company{

  @Override
  public Gpu createGpu(String type){
    return new MsiGpu();
  }
  @Override
  public Monitor createMonitor(String type){
    return new MsiMonitor();
  }
}
class AsusCompany extends Company{
  @Override
  public Gpu createGpu(String type){
    return new AsusGpu();
  }
  @Override
  public Monitor createMonitor(String type){
    return new AsusMonitor();
  }
}
class Main {
  public static void main(String[] args) {
    Company msi = new MsiCompany();
    Gpu msiGpu = msi.createGpu("gpu");
    Monitor msiMonitor = msi.createMonitor("monitor");
    msiGpu.assemble();
    msiMonitor.assemble();

    Company asus = new AsusCompany();
    Gpu asusGpu = asus.createGpu("gpu");
    Monitor asusMonitor = asus.createMonitor("monitor");
    asusGpu.assemble();
    asusMonitor.assemble();
  }
}