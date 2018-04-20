$(function () {
	
	var table = $('<table>').addClass('foo').attr("id", "the_table");
	for(i=0; i<8; i++){
		var row = $('<tr>').addClass('bar');
		table.append(row);
		for(j=0; j<25; j++){
			if(i==0){
				if(j==0){
					var cell = $('<td>').addClass('nonSelectable')
				}
				else{
					var cell = $('<td>').addClass('nonSelectable').text(j-1);
				}
			}
			else{
				if(j==0){
						if(i==1){
							var cell = $('<td>').addClass('nonSelectable').text("Sun");
						}
						else if(i==2){
							var cell = $('<td>').addClass('nonSelectable').text("Mon");
						}
						else if(i==3){
							var cell = $('<td>').addClass('nonSelectable').text("Tue");
						}
						else if(i==4){
							var cell = $('<td>').addClass('nonSelectable').text("Wed");
						}
						else if(i==5){
							var cell = $('<td>').addClass('nonSelectable').text("Thu");
						}
						else if(i==6){
							var cell = $('<td>').addClass('nonSelectable').text("Fri");
						}
						else if(i==7){
							var cell = $('<td>').addClass('nonSelectable').text("Sat");
						}
				}
				else{
					var cell = $('<td>').addClass('selectable');
				}
			}
			var cellNum = (24*i)+j;
			var cellString = cellNum.toString();
			cell.attr("cellid", cellString);
			table.append(cell);
		}
	}
	
	$("body").append(table);
	var isMouseDown = false,
		isHighlighted;

	$(".selectable")
	.mousedown(function () {
	  isMouseDown = true;
	  $(this).toggleClass("highlighted");
	  isHighlighted = $(this).hasClass("highlighted");
	  return false; // prevent text selection
	})
	.mouseover(function () {
	  if (isMouseDown) {
		$(this).toggleClass("highlighted", isHighlighted);
	  }
	})
	.bind("selectstart", function () {
	  return false;
	})

  $(document)
	.mouseup(function () {
	  isMouseDown = false;
	});
	
		var submitButton = $('<button>').addClass('calendarSubmitButton').text("Submit").click(function (){
		var elems = document.getElementsByClassName("selectable");
		var entries = jQuery.makeArray(elems);
		var finalArray = [];
		var z;
		for(z = 0; z < 168; z++){
			if(entries[z].className.indexOf("highlighted")>-1){
				finalArray[z]=1;
			}
			else{
				finalArray[z]=0
			}
		}
		alert(finalArray);
	});
	$("body").append(submitButton);
});