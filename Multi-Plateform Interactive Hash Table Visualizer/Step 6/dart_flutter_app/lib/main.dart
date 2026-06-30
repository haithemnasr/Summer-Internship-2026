import 'package:flutter/material.dart';
import 'widgets/hashtable_visualizer_screen.dart';
void main() {
  runApp(const HashtableApp());
}

class HashtableApp extends StatelessWidget {
  const HashtableApp({super.key});
 
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'XHashtable Visualizer',
      theme: ThemeData(useMaterial3: true, colorSchemeSeed: Colors.indigo),
      home: const HashtableVisualizerScreen(),
    );
  }
}