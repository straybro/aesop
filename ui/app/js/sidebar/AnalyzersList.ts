/// <reference path="../../types/common.d.ts" />

import React = require('react');
import IdName = require('../util/IdName');
var R = React.DOM;

export interface Props {
    activeAnalyzerId: string;
    analyzers: AnalyzerInfo[];
    onAnalyzerClicked: (id:string) => void;
}

export interface AnalyzerInfo {
    idName: IdName;
    componentIdNames: IdName[];
}

class AnalyzersList extends React.Component<Props, {}> {
    private getAnalyzerListItems = () => (
        this.props.analyzers.map((analyzer:AnalyzerInfo) => {
            var clickHandler = (e:React.MouseEvent) => {
                e.preventDefault();
                this.props.onAnalyzerClicked(analyzer.idName.id);
            };
            var components = analyzer.componentIdNames.map((idName: IdName) => R.li({}, R.a({ href: '#' }, idName.name)));
            return (
                R.li({},
                    R.a({ href: '#', onClick: clickHandler },
                        analyzer.idName.name),
                        R.ul({ className: this.props.activeAnalyzerId == analyzer.idName.id ? null : 'collapse' },
                            components))
            );
        })
    );

    render() {
        return (
            R.ul({ className: 'sidebar-nav' },
                this.getAnalyzerListItems())
        );
    }
}
export var Component = React.createFactory(AnalyzersList);