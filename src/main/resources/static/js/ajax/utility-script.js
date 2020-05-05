function jsonToSerialize(serialVal) {
	var res = serialVal.split("&");
	var indexed_array = {};
	var iterator = res.values();
	var arrayProdId = iterator.next().value.split("=");
	var arrayOrderId = iterator.next().value.split("=");
	var iterator1 = arrayProdId.values();
	var iterator2 = arrayOrderId.values();
	var prodName = iterator1.next().value;
	var prodValue = iterator1.next().value;
	var orderName = iterator2.next().value;
	var orderValue = iterator2.next().value;
	console.log(iterator1.next().value);
	console.log(iterator2.next().value);
	console.log(iterator2.next().value);
	var indexed_array = {};
	indexed_array[prodName] = prodValue;
	indexed_array[orderName] = orderValue;
	console.log(indexed_array);
	return indexed_array;
}