class Node {
    constructor(key) {
        this.key = key;
        this.next = null;
    }
}
class HashTable {
    constructor(size = 10) {
        this.size = size;
        this.table = new Array(size).fill(null);
    }

    hash(key){
        let hashValue = 0;
        for (let i = 0; i< key.length; i++){
            hashValue = (hashValue * 31 + key.charCodeAt(i)) % this.size;
        }
        return hashValue;
    }
    add(key) {
        const index = this.hash(key);
        let current = this.table[index];
        while (current !== null) {
            if (current.key === key){
                return;
            }
            current = current.next;
        }
        const newNode = new Node(key);
        newNode.next = this.table[index];
        this.table[index] = newNode;
    }
    delete(key){
        const index = this.hash(key);
        let current = this.table[index];
        let prev = null;
        while (current !== null){
            if (current.key === key){
                if (prev === null){
                    this.table[index] = current.next;
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
    find(key){
        const index = this.hash(key);
        let current = this.table[index];
        while (current !== null){
            if (current.key === key){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    getTable(){
        return this.table;
    }

}
class XHashtableDrawer {
    constructor(canvas, hashtable) {
        this.canvas = canvas;
        this.ctx = canvas.getContext("2d");
        this.hashtable = hashtable;
        this.highlightIndex = -1;
        this.highlightColor = "lightgray";
        this.draw();
    }
    highlight(index, color) {
        this.highlightIndex = index;
        this.highlightColor = color;
        this.draw();
    }
    clearHighlight() {
        this.highlightIndex = -1;
        this.draw();
    }
    draw() {
        const ctx = this.ctx;
        ctx.clearRect(0, 0,this.canvas.width, this.canvas.height);
        const table = this.hashtable.getTable();
        let startX = 20;
        let startY = 30;
        let boxWidth = 80;
        let boxHeight = 30;
        let rowHeight = 60;
        for (let i = 0; i < table.length; i++) {
            let y = startY + i * rowHeight;
            ctx.fillStyle = "black";
            ctx.fillText("[" + i + "]", startX, y + 20);
            let current = table[i];
            let x = startX + 40;
            while (current !== null) {
                if (i === this.highlightIndex) {
                    ctx.fillStyle = this.highlightColor;
                } else {
                    ctx.fillStyle = "lightgray";
                }
                ctx.fillRect(x, y, boxWidth, boxHeight);
                ctx.fillStyle = "black";
                ctx.strokeRect(x, y, boxWidth, boxHeight);
                ctx.fillText(current.key, x + 5, y + 20);

                if (current.next !== null) {
                    ctx.beginPath();
                    ctx.moveTo(x + boxWidth, y + 15);
                    ctx.lineTo(x + boxWidth + 20, y + 15);
                    ctx.stroke();
                }
                x += boxWidth + 20;
                current = current.next;
            }
        }
    }
}
class XHashtableAnimator {
    constructor(drawer) {
        this.drawer = drawer;
        this.timeout = null;
    }
    animate(index, color) {
        if (this.timeout !== null) {
            clearTimeout(this.timeout);
        }
        this.drawer.highlight(index, color);
        this.timeout = setTimeout(() => {
            this.drawer.clearHighlight();
            this.timeout = null;
        }, 1000);
    }
}


const canvas = document.getElementById("canvas");
const text = document.getElementById("keyInput");
const insertButton = document.getElementById("insertButton");
const deleteButton = document.getElementById("deleteButton");
const searchButton = document.getElementById("searchButton");
const table = new HashTable(10);
const drawer = new XHashtableDrawer(canvas, table);
const animator = new XHashtableAnimator(drawer);

insertButton.addEventListener("click", () => {
    const key = text.value.trim();
    if (key !== "") {
        const index = table.hash(key);
        table.add(key);
        animator.animate(index, "green");
        text.value = "";
    }
});
deleteButton.addEventListener("click", () => {
    const key = text.value.trim();
    if (key !== "") {
        const index = table.hash(key);
        table.delete(key);
        animator.animate(index, "red");
        text.value = "";
    }
});
searchButton.addEventListener("click", () => {
    const key = text.value.trim();
    if (key !== "") {
        const index = table.hash(key);
        const found = table.find(key);
        animator.animate(index, "yellow");
        alert(found ? "Found: " + key : "Not found: " + key);
        text.value = "";
    }
});