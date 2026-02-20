/*
See https://github.com/jquery-validation/jquery-validation/issues/379.

The maintainors of jQuery validate are OK validating whether or not 1,000,000 and 1.000.000 are numbers,
but aren't getting into parsing their values (because of international formats), so the min, max, and range
rules don't support anything formatted.
*/
$.validator.addMethod('min', function( value, element, param ) {
    value = numeral(value).value();
    return this.optional(element) || value >= param;
});

$.validator.addMethod('max', function( value, element, param ) {
    value = numeral(value).value();
    return this.optional(element) || value <= param;
});

$.validator.addMethod('range', function( value, element, param ) {
    value = numeral(value).value();
    return this.optional(element) || ( value >= param[0] && value <= param[1] );
});

// https://jqueryvalidation.org/step-method/
$.validator.addMethod('step', function( value, element, param ) {
    value = numeral(value).value();
	var type = $( element ).attr( "type" ),
		errorMessage = "Step attribute on input type " + type + " is not supported.",
		supportedTypes = [ "text", "number", "range" ],
		re = new RegExp( "\\b" + type + "\\b" ),
		notSupported = type && !re.test( supportedTypes.join() ),
		decimalPlaces = function( num ) {
			var match = ( "" + num ).match( /(?:\.(\d+))?$/ );
			if ( !match ) {
				return 0;
			}

			// Number of digits right of decimal point.
			return match[ 1 ] ? match[ 1 ].length : 0;
		},
		toInt = function( num ) {
			return Math.round( num * Math.pow( 10, decimals ) );
		},
		valid = true,
		decimals;

	// Works only for text, number and range input types
	// TODO find a way to support input types date, datetime, datetime-local, month, time and week
	if ( notSupported ) {
		throw new Error( errorMessage );
	}

	decimals = decimalPlaces( param );

	// Value can't have too many decimals
	if ( decimalPlaces( value ) > decimals || toInt( value ) % toInt( param ) !== 0 ) {
		valid = false;
	}

	return this.optional( element ) || valid;
});

$.validator.addMethod('maxlengthb', function(val, elem, param){
	if (val==undefined||val==null||typeof val!=='string') return false;
	if (param==undefined||param==null||typeof param!=='number') return false;
	var bytes = 0;
	for (var i=0;i<val.length;i++){
		if (escape(val.charAt(i)).length>4) bytes+=2;
		else bytes+=1;
	}
	return this.optional(elem)||bytes<=param;
});

$.validator.addMethod('regexp', function( value, element, param ) {
	return param.test(value);
});