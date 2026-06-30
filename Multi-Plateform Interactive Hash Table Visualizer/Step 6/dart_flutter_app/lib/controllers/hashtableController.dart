import 'package:flutter/foundation.dart';
import '../model/XHashtable.dart';


class HashtableController extends ChangeNotifier {
  final XHashtable table;
  HashtableController({int size = 10}) : table = XHashtable(size: size);

  int insert(String key) {
    final index = table.hash(key);
    table.add(key);
    notifyListeners();
    return index;
  }
  int remove(String key) {
    final index = table.hash(key);
    table.delete(key);
    notifyListeners();
    return index;
  }
   ({int index, bool found}) search(String key) {
    final index = table.hash(key);
    final found = table.find(key);
    return (index: index, found: found);
  }
  List<List<String>?> get tableState => table.getTableAsArray();
}