'use strict';

define(['react', 'superagent', '../settings'], function (React, Superagent, Settings) {

    return React.createClass({

        displayName: 'BeforeStart',

        onButtonClick: function onButtonClick(event) {
            this.props.game.getGameId().then(function () {});
        },

        onLogOut: function onLogOut() {
            this.props.game.logOut();
        },

        render: function render() {
            return React.createElement(
                'div',
                null,
                React.createElement(
                    'div',
                    null,
                    React.createElement('img', { src: '/img/bg.png', className: 'bg-layer-1', alt: '' })
                ),
                React.createElement(
                    'div',
                    { className: 'before-start bg-color' },
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
                            { href: '#/e-city', className: 'grey-color' },
                            '\u041F\u0440\u043E\u0434\u043E\u043B\u0436\u0438\u0442\u044C'
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
                            { className: 'text-control-color', href: '#/login', onClick: this.onLogOut },
                            '\u0412\u044B\u0445\u043E\u0434'
                        )
                    )
                )
            );
        }

    });
});
//# sourceMappingURL=before-start.js.map
