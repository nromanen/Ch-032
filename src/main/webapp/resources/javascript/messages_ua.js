$.extend($.validator.messages, {
	required: "Будь ласка, заповніть дане поле",
	remote: "Будь ласка, введіть коректне значення",
	email: "Будь ласка, введіть коректну адресу електронної пошти",
	url: "Будь ласка, введіть коректну адресу",
	date: "Будь ласка, введіть коректну дату",
	dateISO: "Будь ласка, введіть коректну дату в форматі ISO",
	number: "Будь ласка, введіть число",
	digits: "Будь ласка, введіть тільки цифри",
	creditcard: "Будь ласка, введіть номер кредитної карти",
	equalTo: "Будь ласка, повторіть введене значення",
	extension: "Будь ласка, виберіть файл з коретним розширенням",
	maxlength: $.validator.format("Будь ласка, введіть не більше {0} символів"),
	minlength: $.validator.format("Будь ласка, введіть не менше {0} символів"),
	rangelength: $.validator.format("Будь ласка, введіть значення від {0} до {1} символів"),
	range: $.validator.format("Будь ласка, введіть число від {0} до {1}."),
	max: $.validator.format("Будь ласка, введіть число менше, чи рівне {0}"),
	min: $.validator.format("Будь ласка, введіть число більше, чи рівне {0}"),
	lettersonly: "Будь ласка, введіть лише букви"
});