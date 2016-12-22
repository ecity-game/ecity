'use strict';

define(['react', '../components/side-bar', '../components/game-rules', '../components/city-list'], function (React, SideBar, GameRules, CityList) {

    return React.createClass({

        displayName: 'Rules',

        render: function render() {
            return React.createElement(
                'div',
                null,
                React.createElement(SideBar, { game: this.props.game }),
                React.createElement(GameRules, null),
                React.createElement(CityList, null)
            );
        }
    });
});
//# sourceMappingURL=rules.js.map
