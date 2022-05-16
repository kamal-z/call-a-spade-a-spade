
document.getElementById('inputGroupFile1').onchange = function () {
  document.getElementById('labelinputGroupFile1').innerText= this.value.replace('C:\\fakepath\\', '');

};

document.getElementById('inputGroupFile2').onchange = function () {
  document.getElementById('labelinputGroupFile2').innerText= this.value.replace('C:\\fakepath\\', '');

};

var btnSubmit = document.getElementsByClassName('btnSubmit'); 

for (let i = 0; btnSubmit.length; i++) {
	this.btnSubmit[i].onclick = function() {
	  document.getElementById('alert').remove();
	}
};


 
 


 