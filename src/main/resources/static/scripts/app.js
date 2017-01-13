'use strict';

define(['react', 'react-dom', 'react-router', 'q', './pages/login', './pages/e-city', './pages/rules', './pages/library', './pages/before-start', './controller/game', './pages/register'], function (React, ReactDom, ReactRouter, Q, Login, Ecity, Rules, Library, BeforeStart, Game, Register) {

    var Router = ReactRouter.Router;
    var Route = ReactRouter.Route;
    var hashHistory = ReactRouter.hashHistory;

    var props = {
        game: new Game()
    };

    var getComponent = function getComponent(page) {
        return function (route) {
            var defer = Q.defer();

            defer.resolve(function () {
                return {
                    render: function render() {
                        return React.createElement(page, props);
                    }
                };
            });

            return defer.promise;
        };
    };

    var requireLogIn = function requireLogIn(nextState, replace, callback) {
        //  console.log(nextState, replace);
        if (!props.game.isLoggedIn()) {
            replace('/login');
        }
        callback();
    };

    var Page = React.createClass({

        displayName: 'Page',

        render: function render() {
            return React.createElement(
                Router,
                { history: hashHistory },
                React.createElement(Route, { path: '/', getComponent: getComponent(Login) }),
                React.createElement(Route, { path: '/login', getComponent: getComponent(Login) }),
                React.createElement(Route, { path: '/e-city', getComponent: getComponent(Ecity), onEnter: requireLogIn }),
                React.createElement(Route, { path: '/e-city/:name', getComponent: getComponent(Ecity), onEnter: requireLogIn }),
                React.createElement(Route, { path: '/rules', getComponent: getComponent(Rules), onEnter: requireLogIn }),
                React.createElement(Route, { path: '/library', getComponent: getComponent(Library), onEnter: requireLogIn }),
                React.createElement(Route, { path: '/before-start', getComponent: getComponent(BeforeStart), onEnter: requireLogIn }),
                React.createElement(Route, { path: '/register', getComponent: getComponent(Register) })
            );
        }
    });

    Page = React.createFactory(Page);

    ReactDom.render(new Page(), document.getElementById('page'));
});
//# sourceMappingURL=app.js.map
