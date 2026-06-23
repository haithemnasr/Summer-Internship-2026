import java.util.Objects;

public class Pair<T, U> {
    private T first;
    private U second;
    public Pair(T first, U second){
        this.first=first;
        this.second=second;
    }
    public T getfirst(){
        return first;
    }
    public U getsecond(){
        return second;
    }
    @Override
    public boolean equals(Object p){
        if (this == p){
            return true;
        }
        if (p == null || this.getClass() != p.getClass()){
            return false;
        }
        Pair <?,?> pair =(Pair<?,?>) p;
        return Objects.equals(this.first ,pair.first) && Objects.equals(this.second ,pair.second);
    }
    @Override
    public int hashCode(){
        return Objects.hash(first,second);
    }
}