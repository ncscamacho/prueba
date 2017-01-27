(function() {
    'use strict';

    angular
        .module('prueba1')
        .factory('PasswordResetFinish', PasswordResetFinish);

    PasswordResetFinish.$inject = ['$resource'];

    function PasswordResetFinish($resource) {
        var service = $resource('resources/api/account/reset_password/finish', {}, {});

        return service;
    }
})();
