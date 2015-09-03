function dishSelectChange() {
	var x = document.getElementById("dishSelect").value;
	if (x != -1) {
		
			document.getElementById("addButton").setAttribute(	
					
			'href',	"submenuEditAddDish?dailyMenuId="+dailyMenuId+"&consumptionTypeId="+consumptionTypeId+"&dishId="+ x);
			} else {
				
				document.getElementById("addButton").setAttribute('href', "#");
			}

	}


function saveChilds() {
	var ageCatsAndQty = document.getElementById("ageCatsAndQty").submit();
		}

function editChilds() {
			var cats = [ "1", "2", "3", "4" ];
			cats
					.forEach(function(entry) {
						if (document.getElementById("ageCatValue" + entry).value == "") {
							document.getElementById("ageCatValue" + entry).value = 0;
						}
						;
					});
		}
