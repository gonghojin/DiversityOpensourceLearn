import React, { Component } from 'react';
import PhoneInfo from './PhoneInfo';

class PhoneInfoList extends Component {
    static defaultProps = {
        data : [],
        onRemove : () => console.warn("onRemove not defined"),
        onUpdate : () => console.warn("onUpdate not defined")
    }

    /**
     * 다음 LifeCycle API의 사용이 필요한 이유?
     * 단 하나의 값 변경으로 인한, 불필요한 전체 랜더링을 차단하기 위해
     * https://velopert.com/3640
     */
    shouldComponentUpdate(nextProps, nextState) {
        return nextProps.data !== this.props.data;
    }
    render() {
        console.log("render PhoneInfoList");
        const {data, onRemove, onUpdate}  = this.props;
        const list = data.map(
            info => (<PhoneInfo
                key={info.id}
                info={info}
                onRemove = {onRemove}
                onUpdate={onUpdate}
            />)
        );

        return(
            <div>
                {list}
            </div>
        )
    }
}
export default PhoneInfoList;