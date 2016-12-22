'use strict';

define(['react', '../components/side-bar', '../components/city-library', '../components/city-list'], function (React, SideBar, CityLibrary, CityList) {

    return React.createClass({

        displayName: 'Library',

        render: function render() {
            return React.createElement(
                'div',
                null,
                React.createElement(SideBar, { game: this.props.game }),
                React.createElement(CityLibrary, null),
                React.createElement(CityList, null)
            );
        }
    });
});
//# sourceMappingURL=library.js.map
