'use strict';

define(['react', 'superagent', '../settings'], function (React, Superagent, Settings) {

    return React.createClass({

        displayName: 'SideBar',

        propTypes: {
            game: React.PropTypes.object.isRequired
        },

        onButtonClick: function onButtonClick(event) {
            this.props.game.getGameId().then(function () {});
        },

        render: function render() {
            return React.createElement(
                'div',
                { className: 'side-bar bg-color' },
                React.createElement(
                    'div',
                    { className: 'logo' },
                    React.createElement(
                        'p',
                        null,
                        'E-City'
                    )
                ),
                React.createElement(
                    'div',
                    null,
                    React.createElement(
                        'a',
                        { className: 'text-control-color', href: '#/e-city', onClick: this.onButtonClick },
                        '\u041D\u043E\u0432\u0430\u044F \u0438\u0433\u0440\u0430'
                    )
                ),
                React.createElement(
                    'div',
                    null,
                    React.createElement(
                        'a',
                        { className: 'grey-color', href: '#' },
                        '\u041F\u0440\u043E\u0434\u043E\u043B\u0436\u0438\u0442\u044C'
                    )
                ),
                React.createElement(
                    'div',
                    null,
                    React.createElement(
                        'a',
                        { className: 'text-control-color', href: '#/e-city' },
                        '\u0420\u0435\u043A\u043E\u0440\u0434\u044B'
                    )
                ),
                React.createElement(
                    'div',
                    null,
                    React.createElement(
                        'a',
                        { className: 'text-control-color', href: '#/rules' },
                        '\u041F\u0440\u0430\u0432\u0438\u043B\u0430'
                    )
                ),
                React.createElement(
                    'div',
                    null,
                    React.createElement(
                        'a',
                        { className: 'text-control-color', href: '#/library' },
                        '\u0411\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0430'
                    )
                ),
                React.createElement(
                    'div',
                    null,
                    React.createElement(
                        'a',
                        { className: 'text-control-color', href: '#/before-start' },
                        '\u0412\u044B\u0445\u043E\u0434'
                    )
                ),
                React.createElement('div', null)
            );
        }

    });
});
//# sourceMappingURL=side-bar.js.map
