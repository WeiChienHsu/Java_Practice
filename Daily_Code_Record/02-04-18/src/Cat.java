class Cat {
    private int height;
    private String name;

    public Cat(String name){
        this.name = name;
        this.height = height;
    }

    public void setHeight(int height){
        if(height > 10) {
            int tall = 100;
            this.height = tall;
        } else{
            System.out.println("Cat should be taller than 10! ");
            this.height = height;
        }
    }
    public int getHeight() {
        return this.height;
    }


}

