import Vue from "vue"

Vue.filter('phoneNumber', function (phoneNumber) {

    if(!phoneNumber) return phoneNumber

    phoneNumber = phoneNumber.replace(/[^0-9]/g, '')

    let tmp = ''
    if( phoneNumber.length < 4){
        return phoneNumber;
    }
    else if(phoneNumber.length < 7)
    {
        tmp += phoneNumber.substr(0, 3);
        tmp += '-';
        tmp += phoneNumber.substr(3);
        return tmp;
    }
    else if(phoneNumber.length == 8)
    {
        tmp += phoneNumber.substr(0, 4);
        tmp += '-';
        tmp += phoneNumber.substr(4);
        return tmp;
    }
    else if(phoneNumber.length < 10)
    {
        if(phoneNumber.substr(0, 2) =='02') { //02-123-5678            
            tmp += phoneNumber.substr(0, 2);
            tmp += '-';
            tmp += phoneNumber.substr(2, 3);
            tmp += '-';
            tmp += phoneNumber.substr(5);
            return tmp;
        }
    }
    else if(phoneNumber.length < 11)
    {
        if(phoneNumber.substr(0, 2) =='02') { //02-1234-5678            
            tmp += phoneNumber.substr(0, 2);
            tmp += '-';
            tmp += phoneNumber.substr(2, 4);
            tmp += '-';
            tmp += phoneNumber.substr(6);
            return tmp;
        } else {                        //010-123-4567            
            tmp += phoneNumber.substr(0, 3);
            tmp += '-';
            tmp += phoneNumber.substr(3, 3);
            tmp += '-';
            tmp += phoneNumber.substr(6);
            return tmp;
        }
    }
    else { //010-1234-5678        
        tmp += phoneNumber.substr(0, 3);
        tmp += '-';
        tmp += phoneNumber.substr(3, 4);
        tmp += '-';
        tmp += phoneNumber.substr(7);
        return tmp;
    }

})