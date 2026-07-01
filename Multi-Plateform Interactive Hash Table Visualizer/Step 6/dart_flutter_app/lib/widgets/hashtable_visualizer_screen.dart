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
    _controller.addListener(() {
      setState(() {});
    });

    _animController.addStatusListener((status) {
      if (status == AnimationStatus.completed) {
        setState(() {
          _highlightIndex = -1;
        });
      }
    });
  }

  @override
  void dispose() {
    _animController.dispose();
    _keyController.dispose();
    _controller.dispose();
    super.dispose();
  }

  void _triggerAnimation(int index, Color color) {
    setState(() {
      _highlightIndex = index;
      _highlightColor = color;
    });

    _animController.forward(from: 0);
  }

  void _onInsert() {
    final key = _keyController.text.trim();
    if (key.isEmpty) return;
    final index = _controller.insert(key);
    _triggerAnimation(index, Colors.green);
    _keyController.clear();
  }

  void _onDelete() {
    final key = _keyController.text.trim();
    if (key.isEmpty) return;
    final index = _controller.remove(key);
    _triggerAnimation(index, Colors.red);
    _keyController.clear();
  }

  void _onSearch() {
    final key = _keyController.text.trim();
    if (key.isEmpty) return;
    final result = _controller.search(key);
    _triggerAnimation(result.index, Colors.yellow);
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text(result.found ? 'Found: $key' : 'Not found: $key'),
        duration: const Duration(seconds: 2),
      ),
    );
    _keyController.clear();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('XHashtable Visualizer')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            _buildControls(),
            const SizedBox(height: 20),
            _buildVisualization(),
          ],
        ),
      ),
    );
  }

  Widget _buildControls() {
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        SizedBox(
          width: 200,
          child: TextField(
            controller: _keyController,
            decoration: const InputDecoration(hintText: 'Enter a key'),
          ),
        ),
        const SizedBox(width: 10),
        ElevatedButton(onPressed: _onInsert, child: const Text('Insert')),
        const SizedBox(width: 10),
        ElevatedButton(onPressed: _onDelete, child: const Text('Delete')),
        const SizedBox(width: 10),
        ElevatedButton(onPressed: _onSearch, child: const Text('Search')),
      ],
    );
  }

  Widget _buildVisualization() {
    return Row(
      mainAxisSize: MainAxisSize.min,
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        SizedBox(
          width: 800,
          height: 610,
          child: CustomPaint(
            painter: HashtablePainter(
              table: _controller.table.getTable(),
              highlightIndex: _highlightIndex,
              highlightColor: _highlightColor,
            ),
          ),
        ),
        const SizedBox(width: 25),
        _buildSidePanel(),
      ],
    );
  }

  Widget _buildSidePanel() {
    final state = _controller.tableState;
    return SizedBox(
      width: 250,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text('Hash Table State',
              style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          ...List.generate(state.length, (i) {
            final bucket = state[i];
            return Padding(
              padding: const EdgeInsets.only(bottom: 8),
              child: Text(
                bucket == null ? '[$i] Empty' : '[$i] → ${bucket.join(' → ')}',
                style: const TextStyle(fontFamily: 'monospace'),
              ),
            );
          }),
        ],
      ),
    );
  }
}