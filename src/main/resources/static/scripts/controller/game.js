'use strict';

define(['superagent', '../settings', 'q'], function (Superagent, Settings, Q) {

    var Game = function Game() {
        this.loggedIn = false;
        this.gameId = 0;
        this.gameStatus = false;
    };

    Game.prototype.getGameId = function () {
        var _this = this;

        var defer = Q.defer();

        Superagent.get(Settings.host + Settings.api + '/game/new').set('Accept', 'application/json').end(function (error, response) /* arrow function */{
            console.log(error);
            if (!error || response.body.length > 0) {
                _this.gameId = JSON.parse(response.text).id;
                console.log(_this.gameId);

                // Tell all listeners that game id changed
                _this.triggerChangeGameId();

                defer.resolve();
            } else {
                defer.reject();
            }
        });
        return defer.promise;
    };

    Game.prototype.logIn = function (user, password) {
        var _this2 = this;

        var defer = Q.defer();

        Superagent.get(Settings.host + Settings.api + '/user/hello').set('Accept', 'application/json').auth(user, password, { type: 'auto' }).end(function (error, response) /* arrow function */{
            console.log(error);
            if (!error || response.body.length > 0) {
                _this2.loggedIn = true;
                defer.resolve();
            } else {
                _this2.loggedIn = false;
                defer.reject();
            }
        });
        Superagent.get(Settings.host + Settings.api + '/game/status').set('Accept', 'application/json').end(function (error, response) /* arrow function */{
            _this2.gameId = JSON.parse(response.text).id;
            console.log(_this2.gameId);
        });

        return defer.promise;
    };

    Game.prototype.logOut = function () {
        this.loggedIn = false;
    };

    Game.prototype.isLoggedIn = function () {
        return this.loggedIn;
    };

    Game.prototype.onChangeGameId = function (callback) {
        if (!this.callbacks) {
            this.callbacks = [];
        }

        this.callbacks.push(callback);
    };

    Game.prototype.offChangeGameId = function (callback) {
        if (!this.callbacks) {
            return;
        }

        this.callbacks = this.callbacks.filter(function (cb) {
            return cb !== callback;
        });
    };

    Game.prototype.triggerChangeGameId = function () {
        var _this3 = this;

        if (!this.callbacks) {
            return;
        }

        this.callbacks.forEach(function (callback) {
            callback(_this3.gameId);
        });
    };

    return Game;
});
//# sourceMappingURL=game.js.map
