import 'package:flutter/material.dart';
import '../controllers/hashtableController.dart';
import '../painter/XHashtablePainter.dart';

class HashtableVisualizerScreen extends StatefulWidget {
  const HashtableVisualizerScreen({super.key});
 
  @override
  State<HashtableVisualizerScreen> createState() =>
      _HashtableVisualizerScreenState();
}

class _HashtableVisualizerScreenState extends State<HashtableVisualizerScreen> with SingleTickerProviderStateMixin {
  late final HashtableController _controller;
  late final TextEditingController _keyController;
  late final AnimationController _animController;

  int _highlightIndex = -1;
  Color _highlightColor = Colors.grey;
  @override
  void initState() {
    super.initState();
    _controller = HashtableController(size: 10);
    _keyController = TextEditingController();

    _animController = AnimationController(
      vsync: this,
      duration: const Duration(milliseconds: 800),
    );
  }


  

}