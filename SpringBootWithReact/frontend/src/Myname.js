import React, { Component} from 'react';

/*class Myname extends Component {
    static defaultProps = {
        name : "기본이름"
    }

    render() {
        return(
            <fragment>
            <div>
                안녕하세요! 제이름은 <b>{this.props.namecon}</b>입니다.
                <div>제 이름은 <b>{this.props.tong}</b></div>
            </div>
            <div> {this.props.name}</div>
            </fragment>
        );
    }
}*/

const Myname = ({name}) => {
    return (
        <div>
            안녕하세요 ! 제이름은 {name}입니다.
        </div>
    );
};
export default Myname;