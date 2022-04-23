const CLOUD_TYPE = 0;
const LOCAL_TYPE = 1;

let interfaceType = CLOUD_TYPE;
let defaultUrl = "";

if (interfaceType == CLOUD_TYPE) {
    defaultUrl = "http://fms-interface.us-east-1.elasticbeanstalk.com/";
} else {
    defaultUrl = "http://localhost:8080/"; //use this just for server mode
}