document.getElementById('new').addEventListener("click", function () {
    document.querySelector('.bg-modal').style.display = "flex";
});
document.querySelector('.close').addEventListener("click", function () {
    document.querySelector('.bg-modal').style.display = "none";
});
document.getElementById('edit').addEventListener("click", function () {
    document.querySelector('.bg-modal-edit').style.display = "flex";
});
document.querySelector('.close').addEventListener("click", function () {
    document.querySelector('.bg-modal-edit').style.display = "none";
});
document.getElementById('delete').addEventListener("click", function () {
    document.querySelector('.bg-modal-delete').style.display = "flex";
});

document.querySelector('.close').addEventListener("click", function () {
    document.querySelector('.bg-modal-delete').style.display = "none";
});

