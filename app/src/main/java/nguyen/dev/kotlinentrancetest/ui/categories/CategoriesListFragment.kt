package nguyen.dev.kotlinentrancetest.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.GridLayoutManager
import nguyen.dev.kotlinentrancetest.MainGraphDirections
import nguyen.dev.kotlinentrancetest.R
import nguyen.dev.kotlinentrancetest.databinding.CategoriesListViewBinding
import nguyen.dev.kotlinentrancetest.repository.models.Category
import nguyen.dev.kotlinentrancetest.ui.categories.adapter.CategoryAdapter
import nguyen.dev.kotlinentrancetest.ui.categories.adapter.CategoryDetailsLookup
import nguyen.dev.kotlinentrancetest.ui.categories.adapter.CategoryKeyProvider
import nguyen.dev.kotlinentrancetest.utils.viewModel
import nguyen.dev.kotlinentrancetest.viewmodel.CategoriesViewModel

class CategoriesListFragment : Fragment(R.layout.categories_list_view) {
    private val adapter = CategoryAdapter()
    private var _binding: CategoriesListViewBinding? = null
    private val vb get() = _binding!!
    private var tracker: SelectionTracker<Long>? = null
    private var navController: NavController? = null
    private val categoryVM by lazy { viewModel(CategoriesViewModel::class) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoriesListViewBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        initView()
        categoryVM.getListCategories()
    }

    override fun onResume() {
        super.onResume()
        observerLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearObservers()
    }

    private fun clearObservers() {
        categoryVM.getCategoriesSuccessLiveData.removeObservers(this)
        categoryVM.getCategoriesFailLiveData.removeObservers(this)
        categoryVM.clear()
    }

    private fun observerLiveData() {

        categoryVM.getCategoriesSuccessLiveData.observe(this, Observer {
            it ?: return@Observer
            initAdapter(it)
        })
        categoryVM.getCategoriesFailLiveData.observe(this, Observer {
            it?: return@Observer
            Toast.makeText(this.context, "getListFail - $it", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initAdapter(list: List<Category>) {
        vb.listCategories.layoutManager = GridLayoutManager(this.context, 3)
        vb.listCategories.adapter = adapter
        adapter.list = list
        adapter.notifyDataSetChanged()

        tracker = SelectionTracker.Builder<Long>(
            "mySelection",
            vb.listCategories,
            CategoryKeyProvider(vb.listCategories),
            CategoryDetailsLookup(vb.listCategories),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        adapter.tracker = tracker

        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val items = tracker?.selection!!.size()
                    Log.d("itemSelection", "${tracker?.selection}")
                }
            })
    }

    private fun initView() {
        vb.viewBack.setOnClickListener {
            goBack()
        }

    }

    private fun goBack() {
        this.navController?.navigate(MainGraphDirections.actionGlobalSignUpFragment())
    }

}