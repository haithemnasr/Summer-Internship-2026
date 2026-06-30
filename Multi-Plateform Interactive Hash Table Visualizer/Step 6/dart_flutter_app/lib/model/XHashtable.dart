import 'node.dart';

class XHashtable {
  final int size;
  late List<HashNode?> _table;
 
  XHashtable({this.size = 10}) {
    _table = List<HashNode?>.filled(size, null);
  }
  int hash(String key) {
    int hashValue = 0;
    for (int i = 0; i < key.length; i++) {
      hashValue = (hashValue * 31 + key.codeUnitAt(i)) % size;
    }
    return hashValue;
  }
  void add(String key) {
    final index = hash(key);
    HashNode? current = _table[index];
    while (current != null) {
      if (current.key == key) return;
      current = current.next;
    }
    final newNode = HashNode(key);
    newNode.next = _table[index];
    _table[index] = newNode;
  }
  bool delete(String key){
    final index = hash(key);
    HashNode? current =_table[index];
    HashNode? prev;
    while (current != null){
      if (current.key == key){
        if (prev == null){
          _table[index] = current.next;
        }else{
          prev.next = current.next;
        }
        return true;
      }
      prev = current;
      current = current.next;
    }
    return false;
  }
  bool find(String key){
        final index = hash(key);
        HashNode? current = _table[index];
        while (current != null){
            if (current.key == key){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    List<HashNode?> getTable() => _table;
    List<List<String>?> getTableAsArray() {
      return _table.map((bucket) {
        if (bucket == null) return null;
        final result = <String>[];
        HashNode? current = bucket;
        while (current != null) {
          result.add(current.key);
          current = current.next;
        }
        return result;
      }).toList();
    }
}