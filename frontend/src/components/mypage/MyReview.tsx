import React, {useState, useEffect} from 'react'
import MyReviewItem from './MyReviewItem'
import { useDispatch } from 'react-redux'
import { getMyReview } from '../../store/mypageslice'
import store from '../../store'

function MyReview() {
  const dispatch = useDispatch<typeof store.dispatch>()

  const [myReviewList, setMyReviewList] = useState<any>([])

    // 데이터 불러오기
    async function loadReviewData() {
      const MyReview = await dispatch(getMyReview())
      setMyReviewList(MyReview.payload)
    }
  
    useEffect(() => {
      loadReviewData()
    },[])
  return (
    <div>
      <h1>MyReview</h1>
        <MyReviewItem/>
    </div>
  );
}

export default MyReview;
