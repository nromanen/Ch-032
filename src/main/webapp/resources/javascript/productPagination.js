function initUI() {
	// check the correctness of pagination
	if (pages > 1) {
		var linkInPagination;
		if (keyWord == "") {
			linkInPagination = "products?";
		} else {
			linkInPagination = "productsSearch?name=" + keyWord + "&";
		}
		var root = document.getElementById("pagination");
		var start, finish;

		if (!(products.length == 2)) {
			if (!isNaN(pages)) {
				if (pages > 5) {
					if (current <= 3) {
						start = 1;
						finish = 5;
					} else if (current > 3 && current < (pages - 2)) {
						start = (current - 2);
						finish = (current + 2);
					} else {
						start = pages - 4;
						finish = pages;
					}
				} else {
					start = 1;
					finish = pages;
				}

				// create fast backward
				var listItem = document.createElement("li");
				var link = document.createElement("a");
				var span = document.createElement("span");
				link.setAttribute("href", linkInPagination + "page=1");
				span.setAttribute("class", "glyphicon glyphicon-fast-backward");
				span.style.setProperty("line-height", "1.5");
				link.appendChild(span);
				if (current == 1) {
					listItem.setAttribute("class", "disabled");
					link.setAttribute("href", "#");
				}
				listItem.appendChild(link);
				root.appendChild(listItem);

				// create step backward
				var listItem = document.createElement("li");
				var link = document.createElement("a");
				var span = document.createElement("span");
				link.setAttribute("href", linkInPagination + "page="
						+ (current - 1));
				span.setAttribute("class", "glyphicon glyphicon-step-backward");
				span.style.setProperty("line-height", "1.5");
				link.appendChild(span);
				if (current == 1) {
					listItem.setAttribute("class", "disabled");
					link.setAttribute("href", "#");
				}

				listItem.appendChild(link);
				root.appendChild(listItem);

				// create main pagination
				for (var i = start; i <= finish; i++) {
					listItem = document.createElement("li");
					if ((i >= 1) && (i <= pages)) {
						var listItem = document.createElement("li");
						var link = document.createElement("a");
						if (current == i) {
							listItem.setAttribute("class", "active")
						}

						link.setAttribute("href", linkInPagination + "page="
								+ i);
						link.innerHTML = i;
						listItem.appendChild(link);
						root.appendChild(listItem);
					}
				}

				// create step forward
				var listItem = document.createElement("li");
				var link = document.createElement("a");
				var span = document.createElement("span");
				link.setAttribute("href", linkInPagination + "page="
						+ (current + 1));
				span.setAttribute("class", "glyphicon glyphicon-step-forward");
				span.style.setProperty("line-height", "1.5");
				link.appendChild(span);
				if (current >= pages) {
					listItem.setAttribute("class", "disabled");
					link.setAttribute("href", "#");
				}

				listItem.appendChild(link);
				root.appendChild(listItem);

				// create fast forward
				var listItem = document.createElement("li");
				var link = document.createElement("a");
				var span = document.createElement("span");
				link.setAttribute("href", linkInPagination + "page=" + pages);
				span.setAttribute("class", "glyphicon glyphicon-fast-forward");
				span.style.setProperty("line-height", "1.5");
				link.appendChild(span);
				if (current >= pages) {
					listItem.setAttribute("class", "disabled");
					link.setAttribute("href", "#");
				}
				listItem.appendChild(link);
				root.appendChild(listItem);
			}
		}
	}
};

function searchCancel() {
	if (keyWord) {
		document.location.href = "products";
	}
};

function searchProducts() {
	if ($("#keyWord").val()) {
		$("#searchForm").submit();

	}
}
