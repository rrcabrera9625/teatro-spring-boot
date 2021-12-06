function tokenSuccess(err, response) {
    if(err){
        throw err;
    }
    $window.sessionStorage.Authorization = response.body.Authorization;
}