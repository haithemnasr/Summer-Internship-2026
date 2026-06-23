let books = [];
function addBook() {
    let title = document.getElementById("title").value;
    let author = document.getElementById("author").value;
    books.push({
        title: title,
        author: author
    });
    displayBooks();
}
function displayBooks() {
    let list = document.getElementById("bookList");
    list.innerHTML = "";
    for(let book of books) {
        let item = document.createElement("li");
        item.textContent = book.title + " - " + book.author;
        list.appendChild(item);
    }
}
function searchBook() {
    let search = document.getElementById("searchTitle").value;
    for(let book of books) {
        if(book.title === search) {
            alert("Found: " +book.title +" by " +book.author);
            return;
        }
    }
    alert("Book not found");
}