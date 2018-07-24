// Class to represent entire hash table
class HashMap{
    int collisions;
    int noOfEntries;
    int tableSize = 117;
    String[] hTable = new String[tableSize];

    private int getIndex(String key){
        int hashCode =key.hashCode();
            hashCode = hashCode%tableSize;
            hashCode = Math.abs(hashCode);
        return hashCode;
    }

    public void insert(String key){
        int index = getIndex(key);
        if( hTable[index] == null){
            hTable[index] = key;
            noOfEntries++;
            //System.out.println("key " + key + " was inserted at position " + index);
        }
        else{
            int newIndex =  linearProb(key,index);
            hTable[newIndex] = key;
            noOfEntries++;
            //System.out.println("key " + key + " was inserted at position " + newIndex);
        }
    }
    public void noOfCollisions(){
        System.out.println("Number of Collisions: " + collisions);
    }

    public int linearProb(String key,int index) {
        boolean inserted = false;
        int newIndex =0;
        int count =1;
        while(!inserted){
            collisions++;
            newIndex = index + count;
            newIndex = newIndex % tableSize;
            if(hTable[newIndex]==null){
                inserted = true;
            }
            else{
                count++;
            }
        }
        return newIndex;
    }
    public void findKey(String key){
        boolean found = false;
        int count =1;
        int keyIndex = getIndex(key);
        if(hTable[keyIndex] == key){
            System.out.println(key + " was found at position " + keyIndex);
        }
        else{
            while (!found){
                int newIndex = keyIndex +count;
                newIndex = newIndex % tableSize;
                if(hTable[newIndex] == null){
                    System.out.println(key + " was not found");
                    break;
                }
                if(hTable[newIndex] == key){
                    System.out.println(key + " was found at position " + newIndex);
                    break;
                }
                count++;
            }
        }
    }

    public static void main(String[] args){
        String[] dictionaryWords = {"Apple", "John", "keyboard", "penny", "blank", "execution", "will","horizon", "gauge", "level", "faithful", "captain", "more", "grade", "obesity","phase", "star","curve","sabre", "wave", "sleep", "bus", "church", "quark","saboteur" };
        System.out.println("Total number of words " + dictionaryWords.length);
        HashMap hMap = new HashMap();
        for(int i =0; i< dictionaryWords.length; i++){
            hMap.insert(dictionaryWords[i]);
        }
        hMap.noOfCollisions();
        hMap.findKey("execution");
        hMap.findKey("captain");
        hMap.findKey("Assume");
        hMap.findKey("Abs");
    }

}