class Singleton {
    private static volatile Singleton instance; // volatile ensures the Singleton instance is fully constructed before any thread can access it.
    private String data; // username or password

    private Singleton(String data){
        this.data = data;
    }

    // Static creation method to get the singleton instance
    public static Singleton getInstance(String data){
        Singleton result = instance;
        if(result == null){
            synchronized (Singleton.class) { // lock the class to ensure only one thread can execute this block at a time
                result = instance;
                if(result == null){
                    instance = result = new Singleton(data); 
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(Singleton.getInstance("username"));
    }
}