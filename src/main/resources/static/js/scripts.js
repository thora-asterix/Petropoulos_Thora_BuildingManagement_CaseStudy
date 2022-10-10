console.log('hello');

let modal = document.querySelector("#simpleModal");

let linkElement = document.querySelector("#title");

let closeBtn = document.querySelector("#closeBtn");

linkElement.addEventListener("click", openModal);
closeBtn.addEventListener("click", closeModal);

window.onclick = (e) => {
    if (e.target == modal) {
        modal.style.display = "none";
    }        
}

function openModal(){
	modal.style.display = "block";
}

function closeModal(){
	console.log("close modal");
	modal.style.display = "none";
}

function closeModalOutside(e){
	if(e.target == modal){
	console.log("close modal");
	modal.style.display = "none";
	}
}
