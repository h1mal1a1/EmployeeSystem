//Generic - позволяют создавать типобезопасные классы, интерфейсы и методы. Таким образом они решают проблемы:
// проверки типов до этапа компиляции, необходимости приведения типов
public class Container<T> {
    private T _item;

    public void setItem(T item){
        _item = item;
    }
    public T getItem() {
        return _item;
    }
    public boolean isEmpty(){
        return _item == null;
    }
}
