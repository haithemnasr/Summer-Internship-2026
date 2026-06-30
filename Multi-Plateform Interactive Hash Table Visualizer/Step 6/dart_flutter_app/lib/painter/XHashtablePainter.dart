import 'package:flutter/material.dart';
import '../model/node.dart';


class HashtablePainter extends CustomPainter {
  final List<HashNode?> table;
  final int highlightIndex;
  final Color highlightColor;
  static const double startX = 20;
  static const double startY = 30;
  static const double indexBoxWidth = 50;
  static const double boxWidth = 80;
  static const double boxHeight = 30;
  static const double rowHeight = 60;
  static const double gap = 20;

  HashtablePainter({
    required this.table,
    this.highlightIndex = -1,
    this.highlightColor = Colors.grey,
  });

  @override
  void paint(Canvas canvas, Size size) {
    final fillPaint = Paint()..style = PaintingStyle.fill;
    final strokePaint = Paint()
      ..style = PaintingStyle.stroke
      ..color = Colors.black
      ..strokeWidth = 1;
 
    for (int i = 0; i < table.length; i++) {
      final y = startY + i * rowHeight;
      final isHighlighted = i == highlightIndex;

      final indexRect = Rect.fromLTWH(startX, y, indexBoxWidth, boxHeight);
      fillPaint.color = isHighlighted ? highlightColor : Colors.white;
      canvas.drawRect(indexRect, fillPaint);
      canvas.drawRect(indexRect, strokePaint);
      _drawText(canvas, '[$i]', startX + 10, y + 8);
 
      HashNode? current = table[i];
      double x = startX + indexBoxWidth;

      if (current != null) {
        canvas.drawLine(
          Offset(x, y + boxHeight / 2),
          Offset(x + gap, y + boxHeight / 2),
          strokePaint,
        );
      }
      x += gap;

      while (current != null) {
        final boxRect = Rect.fromLTWH(x, y, boxWidth, boxHeight);
        fillPaint.color = isHighlighted ? highlightColor : Colors.grey[300]!;
        canvas.drawRect(boxRect, fillPaint);
        canvas.drawRect(boxRect, strokePaint);
        _drawText(canvas, current.key, x + 5, y + 8);
 
        if (current.next != null) {
          canvas.drawLine(
            Offset(x + boxWidth, y + boxHeight / 2),
            Offset(x + boxWidth + gap, y + boxHeight / 2),
            strokePaint,
          );
        }

      }
    }
  }
  void _drawText(Canvas canvas, String text, double x, double y) {
    final textPainter = TextPainter(
      text: TextSpan(
        text: text,
        style: const TextStyle(color: Colors.black, fontSize: 14),
      ),
      textDirection: TextDirection.ltr,
    );
    textPainter.layout();
    textPainter.paint(canvas, Offset(x, y));
  }

  @override
  bool shouldRepaint(covariant HashtablePainter oldDelegate) {
    if (oldDelegate.highlightIndex != highlightIndex) return true;
    if (oldDelegate.highlightColor != highlightColor) return true;

    if (oldDelegate.table.length != table.length) return true;

    for (int i = 0; i < table.length; i++) {
      if (!_bucketKeysEqual(oldDelegate.table[i], table[i])) return true;
    }
    return false;
  }

  bool _bucketKeysEqual(HashNode? a, HashNode? b) {
    HashNode? nodeA = a;
    HashNode? nodeB = b;
    while (nodeA != null && nodeB != null) {
      if (nodeA.key != nodeB.key) return false;
      nodeA = nodeA.next;
      nodeB = nodeB.next;
    }
    return nodeA == null && nodeB == null;
  }

}