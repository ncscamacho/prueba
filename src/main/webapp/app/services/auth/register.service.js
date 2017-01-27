(function () {
    'use strict';

    angular
        .module('prueba1')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('resources/api/register', {}, {});
    }
})();
